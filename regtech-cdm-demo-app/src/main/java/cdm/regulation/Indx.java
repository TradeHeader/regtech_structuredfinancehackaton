package cdm.regulation;

import cdm.regulation.Indx;
import cdm.regulation.Indx.IndxBuilder;
import cdm.regulation.Indx.IndxBuilderImpl;
import cdm.regulation.Indx.IndxImpl;
import cdm.regulation.Nm;
import cdm.regulation.meta.IndxMeta;
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
@RosettaDataType(value="Indx", builder=Indx.IndxBuilderImpl.class, version="${project.version}")
public interface Indx extends RosettaModelObject {

	IndxMeta metaData = new IndxMeta();

	/*********************** Getter Methods  ***********************/
	Nm getNm();

	/*********************** Build Methods  ***********************/
	Indx build();
	
	Indx.IndxBuilder toBuilder();
	
	static Indx.IndxBuilder builder() {
		return new Indx.IndxBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Indx> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Indx> getType() {
		return Indx.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("nm"), processor, Nm.class, getNm());
	}
	

	/*********************** Builder Interface  ***********************/
	interface IndxBuilder extends Indx, RosettaModelObjectBuilder {
		Nm.NmBuilder getOrCreateNm();
		Nm.NmBuilder getNm();
		Indx.IndxBuilder setNm(Nm nm);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("nm"), processor, Nm.NmBuilder.class, getNm());
		}
		

		Indx.IndxBuilder prune();
	}

	/*********************** Immutable Implementation of Indx  ***********************/
	class IndxImpl implements Indx {
		private final Nm nm;
		
		protected IndxImpl(Indx.IndxBuilder builder) {
			this.nm = ofNullable(builder.getNm()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("nm")
		public Nm getNm() {
			return nm;
		}
		
		@Override
		public Indx build() {
			return this;
		}
		
		@Override
		public Indx.IndxBuilder toBuilder() {
			Indx.IndxBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Indx.IndxBuilder builder) {
			ofNullable(getNm()).ifPresent(builder::setNm);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Indx _that = getType().cast(o);
		
			if (!Objects.equals(nm, _that.getNm())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (nm != null ? nm.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Indx {" +
				"nm=" + this.nm +
			'}';
		}
	}

	/*********************** Builder Implementation of Indx  ***********************/
	class IndxBuilderImpl implements Indx.IndxBuilder {
	
		protected Nm.NmBuilder nm;
	
		public IndxBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("nm")
		public Nm.NmBuilder getNm() {
			return nm;
		}
		
		@Override
		public Nm.NmBuilder getOrCreateNm() {
			Nm.NmBuilder result;
			if (nm!=null) {
				result = nm;
			}
			else {
				result = nm = Nm.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("nm")
		public Indx.IndxBuilder setNm(Nm nm) {
			this.nm = nm==null?null:nm.toBuilder();
			return this;
		}
		
		@Override
		public Indx build() {
			return new Indx.IndxImpl(this);
		}
		
		@Override
		public Indx.IndxBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Indx.IndxBuilder prune() {
			if (nm!=null && !nm.prune().hasData()) nm = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getNm()!=null && getNm().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Indx.IndxBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Indx.IndxBuilder o = (Indx.IndxBuilder) other;
			
			merger.mergeRosetta(getNm(), o.getNm(), this::setNm);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Indx _that = getType().cast(o);
		
			if (!Objects.equals(nm, _that.getNm())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (nm != null ? nm.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "IndxBuilder {" +
				"nm=" + this.nm +
			'}';
		}
	}
}
