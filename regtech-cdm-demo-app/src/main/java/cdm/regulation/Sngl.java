package cdm.regulation;

import cdm.regulation.Indx;
import cdm.regulation.Sngl;
import cdm.regulation.Sngl.SnglBuilder;
import cdm.regulation.Sngl.SnglBuilderImpl;
import cdm.regulation.Sngl.SnglImpl;
import cdm.regulation.meta.SnglMeta;
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
@RosettaDataType(value="Sngl", builder=Sngl.SnglBuilderImpl.class, version="${project.version}")
public interface Sngl extends RosettaModelObject {

	SnglMeta metaData = new SnglMeta();

	/*********************** Getter Methods  ***********************/
	String getIsin();
	Indx getIndx();

	/*********************** Build Methods  ***********************/
	Sngl build();
	
	Sngl.SnglBuilder toBuilder();
	
	static Sngl.SnglBuilder builder() {
		return new Sngl.SnglBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Sngl> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Sngl> getType() {
		return Sngl.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("isin"), String.class, getIsin(), this);
		processRosetta(path.newSubPath("indx"), processor, Indx.class, getIndx());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SnglBuilder extends Sngl, RosettaModelObjectBuilder {
		Indx.IndxBuilder getOrCreateIndx();
		Indx.IndxBuilder getIndx();
		Sngl.SnglBuilder setIsin(String isin);
		Sngl.SnglBuilder setIndx(Indx indx);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("isin"), String.class, getIsin(), this);
			processRosetta(path.newSubPath("indx"), processor, Indx.IndxBuilder.class, getIndx());
		}
		

		Sngl.SnglBuilder prune();
	}

	/*********************** Immutable Implementation of Sngl  ***********************/
	class SnglImpl implements Sngl {
		private final String isin;
		private final Indx indx;
		
		protected SnglImpl(Sngl.SnglBuilder builder) {
			this.isin = builder.getIsin();
			this.indx = ofNullable(builder.getIndx()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("isin")
		public String getIsin() {
			return isin;
		}
		
		@Override
		@RosettaAttribute("indx")
		public Indx getIndx() {
			return indx;
		}
		
		@Override
		public Sngl build() {
			return this;
		}
		
		@Override
		public Sngl.SnglBuilder toBuilder() {
			Sngl.SnglBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Sngl.SnglBuilder builder) {
			ofNullable(getIsin()).ifPresent(builder::setIsin);
			ofNullable(getIndx()).ifPresent(builder::setIndx);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Sngl _that = getType().cast(o);
		
			if (!Objects.equals(isin, _that.getIsin())) return false;
			if (!Objects.equals(indx, _that.getIndx())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (isin != null ? isin.hashCode() : 0);
			_result = 31 * _result + (indx != null ? indx.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Sngl {" +
				"isin=" + this.isin + ", " +
				"indx=" + this.indx +
			'}';
		}
	}

	/*********************** Builder Implementation of Sngl  ***********************/
	class SnglBuilderImpl implements Sngl.SnglBuilder {
	
		protected String isin;
		protected Indx.IndxBuilder indx;
	
		public SnglBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("isin")
		public String getIsin() {
			return isin;
		}
		
		@Override
		@RosettaAttribute("indx")
		public Indx.IndxBuilder getIndx() {
			return indx;
		}
		
		@Override
		public Indx.IndxBuilder getOrCreateIndx() {
			Indx.IndxBuilder result;
			if (indx!=null) {
				result = indx;
			}
			else {
				result = indx = Indx.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("isin")
		public Sngl.SnglBuilder setIsin(String isin) {
			this.isin = isin==null?null:isin;
			return this;
		}
		@Override
		@RosettaAttribute("indx")
		public Sngl.SnglBuilder setIndx(Indx indx) {
			this.indx = indx==null?null:indx.toBuilder();
			return this;
		}
		
		@Override
		public Sngl build() {
			return new Sngl.SnglImpl(this);
		}
		
		@Override
		public Sngl.SnglBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Sngl.SnglBuilder prune() {
			if (indx!=null && !indx.prune().hasData()) indx = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIsin()!=null) return true;
			if (getIndx()!=null && getIndx().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Sngl.SnglBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Sngl.SnglBuilder o = (Sngl.SnglBuilder) other;
			
			merger.mergeRosetta(getIndx(), o.getIndx(), this::setIndx);
			
			merger.mergeBasic(getIsin(), o.getIsin(), this::setIsin);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Sngl _that = getType().cast(o);
		
			if (!Objects.equals(isin, _that.getIsin())) return false;
			if (!Objects.equals(indx, _that.getIndx())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (isin != null ? isin.hashCode() : 0);
			_result = 31 * _result + (indx != null ? indx.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SnglBuilder {" +
				"isin=" + this.isin + ", " +
				"indx=" + this.indx +
			'}';
		}
	}
}
