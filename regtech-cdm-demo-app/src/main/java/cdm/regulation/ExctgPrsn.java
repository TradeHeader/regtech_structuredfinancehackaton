package cdm.regulation;

import cdm.regulation.ExctgPrsn;
import cdm.regulation.ExctgPrsn.ExctgPrsnBuilder;
import cdm.regulation.ExctgPrsn.ExctgPrsnBuilderImpl;
import cdm.regulation.ExctgPrsn.ExctgPrsnImpl;
import cdm.regulation.Prsn;
import cdm.regulation.meta.ExctgPrsnMeta;
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
@RosettaDataType(value="ExctgPrsn", builder=ExctgPrsn.ExctgPrsnBuilderImpl.class, version="${project.version}")
public interface ExctgPrsn extends RosettaModelObject {

	ExctgPrsnMeta metaData = new ExctgPrsnMeta();

	/*********************** Getter Methods  ***********************/
	Prsn getPrsn();

	/*********************** Build Methods  ***********************/
	ExctgPrsn build();
	
	ExctgPrsn.ExctgPrsnBuilder toBuilder();
	
	static ExctgPrsn.ExctgPrsnBuilder builder() {
		return new ExctgPrsn.ExctgPrsnBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ExctgPrsn> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ExctgPrsn> getType() {
		return ExctgPrsn.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("prsn"), processor, Prsn.class, getPrsn());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ExctgPrsnBuilder extends ExctgPrsn, RosettaModelObjectBuilder {
		Prsn.PrsnBuilder getOrCreatePrsn();
		Prsn.PrsnBuilder getPrsn();
		ExctgPrsn.ExctgPrsnBuilder setPrsn(Prsn prsn);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("prsn"), processor, Prsn.PrsnBuilder.class, getPrsn());
		}
		

		ExctgPrsn.ExctgPrsnBuilder prune();
	}

	/*********************** Immutable Implementation of ExctgPrsn  ***********************/
	class ExctgPrsnImpl implements ExctgPrsn {
		private final Prsn prsn;
		
		protected ExctgPrsnImpl(ExctgPrsn.ExctgPrsnBuilder builder) {
			this.prsn = ofNullable(builder.getPrsn()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("prsn")
		public Prsn getPrsn() {
			return prsn;
		}
		
		@Override
		public ExctgPrsn build() {
			return this;
		}
		
		@Override
		public ExctgPrsn.ExctgPrsnBuilder toBuilder() {
			ExctgPrsn.ExctgPrsnBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ExctgPrsn.ExctgPrsnBuilder builder) {
			ofNullable(getPrsn()).ifPresent(builder::setPrsn);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExctgPrsn _that = getType().cast(o);
		
			if (!Objects.equals(prsn, _that.getPrsn())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (prsn != null ? prsn.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExctgPrsn {" +
				"prsn=" + this.prsn +
			'}';
		}
	}

	/*********************** Builder Implementation of ExctgPrsn  ***********************/
	class ExctgPrsnBuilderImpl implements ExctgPrsn.ExctgPrsnBuilder {
	
		protected Prsn.PrsnBuilder prsn;
	
		public ExctgPrsnBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("prsn")
		public Prsn.PrsnBuilder getPrsn() {
			return prsn;
		}
		
		@Override
		public Prsn.PrsnBuilder getOrCreatePrsn() {
			Prsn.PrsnBuilder result;
			if (prsn!=null) {
				result = prsn;
			}
			else {
				result = prsn = Prsn.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("prsn")
		public ExctgPrsn.ExctgPrsnBuilder setPrsn(Prsn prsn) {
			this.prsn = prsn==null?null:prsn.toBuilder();
			return this;
		}
		
		@Override
		public ExctgPrsn build() {
			return new ExctgPrsn.ExctgPrsnImpl(this);
		}
		
		@Override
		public ExctgPrsn.ExctgPrsnBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExctgPrsn.ExctgPrsnBuilder prune() {
			if (prsn!=null && !prsn.prune().hasData()) prsn = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPrsn()!=null && getPrsn().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExctgPrsn.ExctgPrsnBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ExctgPrsn.ExctgPrsnBuilder o = (ExctgPrsn.ExctgPrsnBuilder) other;
			
			merger.mergeRosetta(getPrsn(), o.getPrsn(), this::setPrsn);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExctgPrsn _that = getType().cast(o);
		
			if (!Objects.equals(prsn, _that.getPrsn())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (prsn != null ? prsn.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExctgPrsnBuilder {" +
				"prsn=" + this.prsn +
			'}';
		}
	}
}
