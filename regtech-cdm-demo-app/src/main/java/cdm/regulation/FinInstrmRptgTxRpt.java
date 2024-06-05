package cdm.regulation;

import cdm.regulation.FinInstrmRptgTxRpt;
import cdm.regulation.FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder;
import cdm.regulation.FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilderImpl;
import cdm.regulation.FinInstrmRptgTxRpt.FinInstrmRptgTxRptImpl;
import cdm.regulation.Tx;
import cdm.regulation.meta.FinInstrmRptgTxRptMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="FinInstrmRptgTxRpt", builder=FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilderImpl.class, version="${project.version}")
public interface FinInstrmRptgTxRpt extends RosettaModelObject {

	FinInstrmRptgTxRptMeta metaData = new FinInstrmRptgTxRptMeta();

	/*********************** Getter Methods  ***********************/
	Tx getTx();

	/*********************** Build Methods  ***********************/
	FinInstrmRptgTxRpt build();
	
	FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder toBuilder();
	
	static FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder builder() {
		return new FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FinInstrmRptgTxRpt> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FinInstrmRptgTxRpt> getType() {
		return FinInstrmRptgTxRpt.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("tx"), processor, Tx.class, getTx());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FinInstrmRptgTxRptBuilder extends FinInstrmRptgTxRpt, RosettaModelObjectBuilder {
		Tx.TxBuilder getOrCreateTx();
		Tx.TxBuilder getTx();
		FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder setTx(Tx tx);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("tx"), processor, Tx.TxBuilder.class, getTx());
		}
		

		FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder prune();
	}

	/*********************** Immutable Implementation of FinInstrmRptgTxRpt  ***********************/
	class FinInstrmRptgTxRptImpl implements FinInstrmRptgTxRpt {
		private final Tx tx;
		
		protected FinInstrmRptgTxRptImpl(FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder builder) {
			this.tx = ofNullable(builder.getTx()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("tx")
		public Tx getTx() {
			return tx;
		}
		
		@Override
		public FinInstrmRptgTxRpt build() {
			return this;
		}
		
		@Override
		public FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder toBuilder() {
			FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder builder) {
			ofNullable(getTx()).ifPresent(builder::setTx);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FinInstrmRptgTxRpt _that = getType().cast(o);
		
			if (!Objects.equals(tx, _that.getTx())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (tx != null ? tx.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FinInstrmRptgTxRpt {" +
				"tx=" + this.tx +
			'}';
		}
	}

	/*********************** Builder Implementation of FinInstrmRptgTxRpt  ***********************/
	class FinInstrmRptgTxRptBuilderImpl implements FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder {
	
		protected Tx.TxBuilder tx;
	
		public FinInstrmRptgTxRptBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("tx")
		public Tx.TxBuilder getTx() {
			return tx;
		}
		
		@Override
		public Tx.TxBuilder getOrCreateTx() {
			Tx.TxBuilder result;
			if (tx!=null) {
				result = tx;
			}
			else {
				result = tx = Tx.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("tx")
		public FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder setTx(Tx tx) {
			this.tx = tx==null?null:tx.toBuilder();
			return this;
		}
		
		@Override
		public FinInstrmRptgTxRpt build() {
			return new FinInstrmRptgTxRpt.FinInstrmRptgTxRptImpl(this);
		}
		
		@Override
		public FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder prune() {
			if (tx!=null && !tx.prune().hasData()) tx = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTx()!=null && getTx().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder o = (FinInstrmRptgTxRpt.FinInstrmRptgTxRptBuilder) other;
			
			merger.mergeRosetta(getTx(), o.getTx(), this::setTx);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FinInstrmRptgTxRpt _that = getType().cast(o);
		
			if (!Objects.equals(tx, _that.getTx())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (tx != null ? tx.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FinInstrmRptgTxRptBuilder {" +
				"tx=" + this.tx +
			'}';
		}
	}
}
